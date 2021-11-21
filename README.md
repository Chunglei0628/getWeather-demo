# getweather-demo
1. Development Environment
   1. IntelliJ IDEA 2021.2.3 (Community Edition)
   2. openJdk 11
   3. gradle-7.1
2. How to config
   1. Environment variable can be set up in \src\main\resources\application.yml
   2. Environment variable explanation
      1. temperatureUnit: Temperature Unit, available value [celsius, kelvin, fahrenheit]
      2. cacheClearSchedule: Schedule of cache clear, cron expressions
      3. apiKey: Api Key
      4. enable: Is corresponding source enabled, Y = enabled
      5. sequence: Sequence of source
3. How to start the program
   1. Run/debug the file: \src\main\kotlin\com\demo\weathercheck\WeatherCheckApplication.kt
4. No test case is developed