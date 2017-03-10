MaterialPreference
============
## ScreenShot
![screenshot](https://github.com/ksxkq/MaterialPreference/blob/master/screenshot/4EE33D379F5B22E6AAB3FD7F937996D8.png)

## 使用方法

The Gradle dependency is available via jCenter.
```gradle
dependencies {
	// ... other dependencies here
    compile 'com.ksxkq:materialpreference:1.0.4'
}
```

布局文件中添加
```xml
<com.ksxkq.materialpreference.widget.PreferenceContainer
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

```java
PreferenceContainer container = (PreferenceContainer) findViewById(container);
```

* CategoryPreference
```java
container.addCategoryPreference("key", "title");
```

* SwitchPreference
```java
container.addSwitchPreference("key", "title");
```

* CheckboxPreference
```java
container.addCheckBoxPreference("key", "title");
```

* ScreenPreference
```java
container.addScreenPreference("key", "title");
```

* SeekBarPreference
```java
container.addScreenPreference("key", "title", defaultValue, maxValue);
```

* ListPreference
```java
container.addCategoryPreference("key", "title", itemNames, itemValues);
```
