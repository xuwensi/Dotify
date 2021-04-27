# HW3 - Fragments, Activity Lifecycle, & Rotation

## Dotify by Wensi Xu

This is a music player application running on Android. The app shows a list of songs. Users can also see a detailed page of a song which displays the user name, album cover, name of the song playing, the artist name, and the number of plays. They can also see user profile, song statistics, and creator info in setting.

## Extra credit
I have completed extra credit #1 and #2

1. Every Fragment (other than SettingsFragment) has a Up/back button in the top right corner. Clicking
will take you back to the previous fragment.
2. Instead of Settings <Button> in your layout from requirement #1, make a Settings "Action Button” in
the app bar (header bar at the top).

## Screenshots

<img src="screenshots/settingFragment.png" alt="Screenshot of settings" height="500" />

<img src="screenshots/statFragment.png" alt="Screenshot of song statistics" height="500" />

## Installation & Usage
When users click on a song, a mini player on pop up at the bottom, showing the title and artist of the song and a "SHUFFLE" button. The "SHUFFLE" button will shuffle the list of songs.

If users tap on the mini player, the app will navigate to a song detailed page. This page allows users to skip to the previous/next track. Every time a user clicks on the play button, the number of plays will increment by 1.

Users can click on setting to navigate to a setting page, displaying user profile, song statistics, and creator info.