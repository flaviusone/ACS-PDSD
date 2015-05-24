package ro.pub.cs.systems.pdsd.lab05.sample11listfragment.view;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.R;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.controller.CartoonCharacterAdapter;
import ro.pub.cs.systems.pdsd.lab05.sample11listfragment.model.CartoonCharacter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CartoonCharacterFragment extends ListFragment {
	
	List<CartoonCharacter> cartoonCharacter;
	
	public CartoonCharacterFragment() {
		this.cartoonCharacter = new ArrayList<CartoonCharacter>();
	}
	
	public void setCartoonCharacters(List<CartoonCharacter> cartoonCharacter) {
		this.cartoonCharacter = cartoonCharacter;
	}
	
	public List<CartoonCharacter> getCartoonCharacters() {
		return this.cartoonCharacter;
	}	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_cartoon_character, container, false);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		CartoonCharacterAdapter cartoonCharacterAdapter = new CartoonCharacterAdapter(getActivity(), cartoonCharacter);
		setListAdapter(cartoonCharacterAdapter);	
	}
}
