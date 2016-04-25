package com.cgossip.psyphysics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

private AdView adView;
	private GameFragment gameFragment;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_layout);

		gameFragment = new GameFragment();
		FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
		tr.replace(R.id.GameView, gameFragment);
		tr.commit();

		adView = (AdView) findViewById(R.id.adView);

		AdRequest adRequest = new AdRequest.Builder()
				.tagForChildDirectedTreatment(true)
				.build();

		adView.loadAd(adRequest);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameFragment.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void exit() {
	}

	@Override
	public void onPause() {
		if (adView != null) adView.pause();
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) adView.resume();
	}

	@Override
	public void onDestroy() {
		if (adView != null) adView.destroy();
		super.onDestroy();
	}
}
