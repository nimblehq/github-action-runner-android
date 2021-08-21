# GitHub Actions Runner for Android

The repository for proof of concept of [Custom Runner for Github Actions (Android) initiative][initiative]

[initiative]: https://www.notion.so/nimblehq/Custom-Runner-for-Github-Actions-Android-bb4c0b6310184e44afe9b3bea0989a57?d=72eb950e-33be-482b-8e17-40c92023073d

## Setup self-hosted runner

### Prerequisites

- Machine running macOS from [MacStadium] or a local one.
- Android Studio installation is recommended to facilitate the installation of other required programs such as Java, Android SDK, and AVD (needed for running the UI test).

[MacStadium]: https://www.macstadium.com/

### Add a self-hosted runner to GitHub repository

1. Open the **Settings** menu of the repository. Go to **Actions** -> **Runners** -> click on the **Add runner** button.
2. Make sure the Operating System dropdown shows **macOS** as a selected OS, then run all of the listed commands from the instruction in your machine respectively and specify configurations when prompted.
3. (Optional) In the directory where the runner was installed, run the following command to install **svc**, which is a program that can help to check and manage the status of the runner.

   ```
   ./svc.sh install
   ```

4. In the directory where the runner was installed, run the following command to launch and inform the runner to listening for the upcoming jobs.

   ```
   ./run.sh
   ```

### Giving the ability to run on a self-hosted runner to workflows

Define the following key to all the jobs that we target to run on a self-hosted runner.

```
runs-on: self-hosted
```

> 📓 Note that we can use the label feature to specify which workflows will run only on which machines. Assigning labels can be done when configuring a runner using the command line or with the UI via the GitHub repository Settings page. To specify labels to a workflow, for example, define the following key to all the jobs that we target to run on self-hosted runners with a **mini-1** label.
>
> ```
> runs-on: [self-hosted, mini-1]
> ```

### Known issues

- One runner can only execute one job at a time. To run multiple jobs or workflows simultaneously, we need to consider adding a new runner in the same or different machine.
- There is an issue related to `actions/cache` in a low-performance machine since the default `upload-chunk-size` value is too big. In this case, we should consider reducing this value to the point that such an issue is gone.
- In case there's an error related to being incapable of finding the Android SDK. We can resolve it by specifying the Android SDK path into the `ANDROID_SDK_ROOT` environment variable, for example:

  ```
  export ANDROID_SDK_ROOT=/Users/username/Library/Android/sdk
  ```

## License

This project is Copyright (c) 2014 and onwards. It is free software,
and may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## About

![Nimble](https://assets.nimblehq.co/logo/dark/logo-dark-text-160.png)

This project is maintained and funded by Nimble.

We love open source and do our part in sharing our work with the community!
See [our other projects][community] or [hire our team][hire] to help build your product.

[community]: https://github.com/nimblehq
[hire]: https://nimblehq.co/
