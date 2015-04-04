package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.view;

import java.io.InputStream;
import java.util.ArrayList;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.R;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.controller.CartoonAdapter;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.model.Cartoon;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.utils.CartoonsXmlParser;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

public class CartoonFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_cartoon, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try {
			CartoonsXmlParser cartoonXmlParser = new CartoonsXmlParser();	
			InputStream inputstream = getActivity().getAssets().open("cartoons.xml");
		    ArrayList<Cartoon> cartoons = cartoonXmlParser.parse(inputstream);
		    CartoonAdapter cartoonAdapter = new CartoonAdapter(getActivity(), cartoons);
		    setListAdapter(cartoonAdapter);
		}
	    catch (Exception exception) {
	    	Log.println(Log.ERROR, "error", exception.getMessage());
	    }		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		WindowManager windowManager = getActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		Point dimension = new Point();
		display.getSize(dimension);
		
		Cartoon cartoonContent = (Cartoon)getListAdapter().getItem(position);
		CartoonCharacterFragment cartoonCharacterFragment = new CartoonCharacterFragment();
		cartoonCharacterFragment.setCartoonCharacters(cartoonContent.getCharacters());
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		if (dimension.x <= dimension.y) {
			fragmentTransaction.replace(android.R.id.content, cartoonCharacterFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.hide(this);
		}
		else
			fragmentTransaction.replace(R.id.fragment2, cartoonCharacterFragment);
		fragmentTransaction.commit();
	}
}
