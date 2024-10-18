# MSLib
마인크래프트 플러그인을 개발할 때 개인적으로 사용하려고 개발한 라이브러입니다.<br>
해당 깃헙 레포지토리에 올라오는 모든 플러그인에는 해당 라이브러리가 사용됩니다.

# 연동 방법
아래는 플러그인과 연동할 수 있는 방법들입니다.<br>
해당 라이브러리는 Maven Central(중앙 저장소)에 배포되어 있습니다.

#### Maven
```xml
<dependency>
    <groupId>io.github.devlone64</groupId>
    <artifactId>MSLib</artifactId>
    <version>1.3.0</version>
</dependency>
```

#### Gradle
```Groovy
implementation 'io.github.devlone64:MSLib:1.3.0'
```

#### Gradle Kotlin
```kotlin
implementation("io.github.devlone64:MSLib:1.3.0")
```

<br>

# 사용 방법
아래는 라이브러리를 플러그인 내에서 사용하는 방법입니다.

```java
import io.github.devlone64.MSLib.MSPlugin;

public class Main extends MSPlugin {
    
    @Override
    public void onInit() { }
    
    @Override
    public void onStart() { }
    
    @Override
    public void onStop() { }
    
}
```