package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.view;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class CartoonActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cartoon);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		MenuItem menuItem;
		SubMenu subMenu = menu.addSubMenu(0, 0, 100, R.string.operations);
		menuItem = subMenu.add(0, 1, 100, R.string.create);
		menuItem.setIcon(R.drawable.create);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menuItem = subMenu.add(0, 2, 100, R.string.update);
		menuItem.setIcon(R.drawable.update);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menuItem = subMenu.add(0, 3, 100, R.string.delete);
		menuItem.setIcon(R.drawable.delete);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menuItem = subMenu.add(0, 4, 100, R.string.search);
		menuItem.setIcon(R.drawable.search);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menuItem = menu.add(1, 5, 100, R.string.settings);
		menuItem.setIcon(R.drawable.settings);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.setGroupVisible(1, true);
		menu.setGroupEnabled(1, true);
		menu.setGroupCheckable(1, false, false);
		menuItem = menu.add(0, 6, 100, R.string.about);
		menuItem.setIcon(R.drawable.about);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

}
