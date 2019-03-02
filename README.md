# ChipEditText

Chips from Material Design with EditText.

[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) [![](https://jitpack.io/v/vitoksmile/ChipEditText.svg)](https://jitpack.io/#vitoksmile/ChipEditText)

## Sample
<img src="https://github.com/vitoksmile/ChipEditText/blob/master/app/sample.gif?raw=true" width="268" height="560" />

## Add to your project
The first step, add JitPack repository to your root build.gradle file (not module build.gradle file):
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
The second step, add the library to your module build.gradle:
```
dependencies {
	implementation 'com.github.vitoksmile:ChipEditText:1.0.0'
}
```

## Usage
Check out the sample app, to see more details.

1) Add `ChipEditTextLayout` to your layout:
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.vitoksmile.widget.chipedittext.ChipEditTextLayout
        android:id="@+id/chipEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hint="Username" />

</FrameLayout>
```

2) Add `ChipEditTextLayout.Interaction` to your controller (example, Activity) to register callbacks: 
- `onDoneClicked` - when user clicked on done button on keyboard;
- `onRemoveClicked ` - when user clicked on remove button on chip.
```Kotlin
class MainActivity : AppCompatActivity(), ChipEditTextLayout.Interaction {

	private val users = linkedSetOf<String>()
	
	private fun initChips() {
		chipEditText.interaction = this
	}
	
	override fun onDoneClicked(text: String): Boolean {
		if (text.isEmpty()) {
			Toast.makeText(this, "Username should be not empty", Toast.LENGTH_SHORT).show()
			return false
		}
		
		// Add new user
		users.add(text)
		
		// Notify edit text
		chipEditText.setChips(users.map { Chip(it) })
		return true
	}
	
	override fun onRemoveClicked(chip: Chip): Boolean {
		users.remove(chip.text)
		return true
	}
}
```

### Todos
- More customization and styles.