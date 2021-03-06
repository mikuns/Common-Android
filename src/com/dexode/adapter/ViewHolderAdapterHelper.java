package com.dexode.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.dexode.util.Assert;

/**
 * Created by Dawid Drozd aka Gelldur on 09.02.15.
 */
public class ViewHolderAdapterHelper {

	public ViewHolderAdapterHelper(final Adapter adapter) {
		_adapter = adapter;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Object holder = null;

		if (convertView == null || convertView.getTag() == null) {
			convertView = _adapter.getInflatedRow(position, parent);
			holder = _adapter.getNewHolder(position);
			_adapter.inflateHolder(holder, convertView, position);
			convertView.setTag(holder);
		} else {
			holder = convertView.getTag();
		}

		final View view = _adapter.setHolder(holder, convertView, parent, position);
		Assert.check(view != null, "setHolder must return view");
		return view;
	}

	public static interface Adapter {

		/**
		 * Here you must only inflate new view of a row
		 *
		 * @return new inflated view
		 */
		@NonNull
		public View getInflatedRow(int position, ViewGroup parent);

		/**
		 * Only return your new Holder
		 *
		 * @return
		 */
		@NonNull
		public Object getNewHolder(int position);

		/**
		 * Initialize your fields with values for example textview.setText() and other jobs here
		 *
		 * @param yourHolder
		 * @param view
		 * @param position
		 *
		 * @return return @param view or other view
		 */
		@NonNull
		public View setHolder(Object yourHolder, View view, ViewGroup parent, int position);

		/**
		 * Simply initiate you fields in holder by findingViewById in view object
		 *
		 * @param yourHolder
		 * @param view
		 * @param position
		 */
		public void inflateHolder(Object yourHolder, View view, int position);
	}

	private Adapter _adapter;

}
