# github-profile-app
An Android app fetching and displaying a single Github profile. The app uses the Github GraphQl API.

# Setup
When checking out the project you'll need to add your own github api token. Generate one [here](https://github.com/settings/tokens/new). Copy `config.properties.example` to `config.properties` and add your key into `GITHUB_API_TOKEN`

# Warning
Currently error handling is not implemented. This means that whenever an error occurs you'll probably get a white screen.

Also you might get a warning since the project uses java 11. You can change java version the project uses in `Preferences` -> `Build,Execution, Deployment` -> `Build Tools` -> `Gradle`. The latest Android studio version should have java 11 bundled.
