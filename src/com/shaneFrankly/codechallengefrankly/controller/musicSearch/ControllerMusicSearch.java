package com.shaneFrankly.codechallengefrankly.controller.musicSearch;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;

import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.shaneFrankly.codechallengefrankly.customObj.MusicListAdapter;
import com.shaneFrankly.codechallengefrankly.customObj.SimpleTextWatcher;
import com.shaneFrankly.codechallengefrankly.database.sql.music.MusicContentProvider;
import com.shaneFrankly.codechallengefrankly.database.sql.music.MusicSQLiteOpenHelper;
import com.shaneFrankly.codechallengefrankly.view.musicSearch.FragmentMusicSearch;

public class ControllerMusicSearch extends FragmentMusicSearch implements OnDismissCallback{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getMusicListView().setAdapter(new MusicListAdapter(getActivity(), createSearchCursor(), 0));
		enableSwipable(this);
		
		//register listeners
		getButtonSave().setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				saveToDatabase(getEditTextTitle().getText().toString(), getEditTextArtist().getText().toString(), getEditTextAlbum().getText().toString());
				performSearch();
			}
		});

		getEditTextTitle().addTextChangedListener(new SimpleTextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				performSearch();
			}
		});

		getEditTextArtist().addTextChangedListener(new SimpleTextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				performSearch();
			}
		});

		getEditTextAlbum().addTextChangedListener(new SimpleTextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				performSearch();
			}
		});

	}
	
	//search cursor that used to perform search
	private Cursor createSearchCursor(){
		return getActivity().getContentResolver().query(
				MusicContentProvider.CONTENT_URI, 
				null, 
				MusicSQLiteOpenHelper.TITLE + " like ? AND " + MusicSQLiteOpenHelper.ARTIST + " like ? AND " + MusicSQLiteOpenHelper.ALBUM + " like ?", 
				new String[]{getEditTextTitle().getText().toString() + "%", getEditTextArtist().getText().toString() + "%", getEditTextAlbum().getText().toString() + "%"}, 
				MusicSQLiteOpenHelper.ID);
	}

	private void performSearch(){
		MusicListAdapter musicListAdapter = (MusicListAdapter)getMusicListView().getAdapter();
		musicListAdapter.changeCursor(createSearchCursor());
		enableSwipable(this);
	}

	private void saveToDatabase(String title, String artist, String album){
		ContentValues values = new ContentValues();
		values.put(MusicSQLiteOpenHelper.TITLE, title);
		values.put(MusicSQLiteOpenHelper.ARTIST, artist);
		values.put(MusicSQLiteOpenHelper.ALBUM, album);
		getActivity().getContentResolver().insert(MusicContentProvider.CONTENT_URI, values);
	}

	@Override
	public void onDismiss(AbsListView absListView, int[] reverseSortedPositions) {
		for (int position: reverseSortedPositions ) {
			Cursor cursor = (Cursor) absListView.getItemAtPosition(position);
			long id = cursor.getLong(cursor.getColumnIndex(MusicSQLiteOpenHelper.ID));
			getActivity().getContentResolver().delete(MusicContentProvider.CONTENT_URI, MusicSQLiteOpenHelper.ID + " =? ", new String[]{"" + id});
		}
		performSearch();
	}
}
