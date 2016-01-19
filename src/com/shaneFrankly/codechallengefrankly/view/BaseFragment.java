package com.shaneFrankly.codechallengefrankly.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment{
	protected List<OnViewCreatedListener> onViewCreatedListeners;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//getView() might be null if this fragment is a fragment holder (viewless)
		if (onViewCreatedListeners != null && onViewCreatedListeners.size() > 0) {
			for (OnViewCreatedListener onViewCreatedListener : onViewCreatedListeners) {
				onViewCreatedListener.onViewCreated(view, savedInstanceState, this);
			}
		}
	}

	public interface OnViewCreatedListener{
		void onViewCreated(View view, Bundle savedInstanceState, Fragment fragment);
	}

	public void ensureMethodsAreCalledAfterViewCreated(OnViewCreatedListener onViewCreatedListener){
		if (getView() != null) {
			//if view is not null, just fire it
			onViewCreatedListener.onViewCreated(getView(), null, this);
		} else {
			//otherwise add to listener list and fire later
			addOnViewCreatedListener(onViewCreatedListener);
		}
	}

	public void addOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener){
		if (onViewCreatedListeners == null) onViewCreatedListeners = new ArrayList<OnViewCreatedListener>();
		onViewCreatedListeners.add(onViewCreatedListener);
	}
}
