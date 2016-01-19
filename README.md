# Code Challenge

Create an Android application that shows:

● input fields for the user to specify a song’s title, artist, and album

● a button to save the specified song information and add it to the list

● a list of songs showing all previously saved songs, each list item showing the song’s title, artist, and album

● an input field for a real time search filter of the list which matches on the song title, artist, and album fields


In portrait mode, the list should be shown in the top half of the screen and the input fields should be centered in the bottom half of the screen. In landscape mode, the list should be shown in the left half and the other fields should be centered in the right half.

Please ensure that any text in the song title, artist, and album input fields is preserved when the device is rotated, the app is backgrounded, the app is exited, etc. When the app is launched again, the text in the aforementioned song input fields should be restored.

Note that you are free to use whichever persistent storage seems most appropriate for storing the list of songs and the text of the song title, artist, and album fields. Additionally, the persistent storage you decide to use for each does not need to be the same. Remember, it’s not just about whether you can solve the problem, but also about how you solve the problem, so please structure and write your code the way we could expect you to on the job. This includes applying any Android/Java best practices that make sense for the solution (or providing some convincing arguments in comments for why certain so­called “best practices” were not used or were avoided).

Bonus​: Add a natural way to remove existing songs from the list (and from persistent storage).
