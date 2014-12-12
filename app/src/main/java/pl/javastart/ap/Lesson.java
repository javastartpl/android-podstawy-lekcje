package pl.javastart.ap;

import android.app.Activity;

public class Lesson {
	private String name;
	private Class<? extends Activity> activity;

	public Lesson(String name, Class<? extends Activity> activity) {
		this.name = name;
		this.activity = activity;
	}

	public Class<? extends Activity> getActivity() {
		return activity;
	}

	@Override
	public String toString() {
		return name;
	}
}
