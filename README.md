# GitHub Actions Runner for Android

The repository for proof of concept of [Custom Runner for Github Actions (Android) initiative](https://www.notion.so/nimblehq/Custom-Runner-for-Github-Actions-Android-bb4c0b6310184e44afe9b3bea0989a57?d=72eb950e-33be-482b-8e17-40c92023073d)

## Setup custom runner

### Prerequisites

- Machine running macOS from [MacStadium](https://www.macstadium.com/) or a physical one
- Android Studio installation is recommended as it will help to install all the required Java, Android
  SDK, also the AVD which needed for running UI test

### Add custom (self-hosted) runner

1. Open the **Settings** menu of the repository. Go to **Actions** -> **Runners** -> click on the **Add runner** button
2. Make sure the Operating System dropdown shows **macOS** as a selected item, then run all of the below commands in your machine in order. Specify configurations when prompted.
3. (Optional) In the directory where the runner was installed, run `./svc.sh install`. This program is for help checking the status of the runner.
4. In the directory where the runner was installed, run `./run.sh` to open the runner to listening for the upcoming jobs.

### Giving the ability to run on a self-hosted runner to workflow

Define `runs-on: self-hosted` to all the jobs we want to be run on a self-hosted runner.

### Known issues

- One runner can only execute one job at a time. To run multiple jobs or workflows simultaneously, we might need to consider adding a new runner in the same or different machine.
- There is an issue related to `actions/cache` in a low-performance machine as the default `upload-chunk-size` value is too big for such a machine. Consider reducing this value when facing such an issue.
- If there's an error related to being incapable of finding the Android SDK. We might need to specify the Android SDK path into `ANDROID_SDK_ROOT` environment variable, for example: `export ANDROID_SDK_ROOT=/Users/username/Library/Android/sdk`.
- In a low-performance machine, Creating an AVD from scratch every time running the Android emulator might be a better solution in terms of execution time as the speed for retrieving cache is low in such a machine.

## About

![Nimble](https://assets.nimblehq.co/logo/dark/logo-dark-text-160.png)

This project is maintained and funded by Nimble.
