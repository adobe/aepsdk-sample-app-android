/*
 Copyright 2020 Adobe
 All Rights Reserved.

 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it.
 */

package com.adobe.marketing.mobile.sampleapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adobe.marketing.mobile.sampleapp.platform.ProductCart;
import com.adobe.marketing.mobile.sampleapp.platform.ProductContent;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewAdapter
	extends RecyclerView.Adapter<ItemViewAdapter.ProductViewHolder> {

	private final List<ProductContent.ProductItem> mValues;
	// Item is clicked in the list view
	private final View.OnClickListener mOnClickListener;

	public ItemViewAdapter(final List<ProductContent.ProductItem> items,
						   final View.OnClickListener listener) {
		mValues = items;
		mOnClickListener = listener;
	}

	@Override
	public ProductViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.item_list_content, parent, false);
		return new ProductViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ProductViewHolder holder, final int position) {
		holder.mItem = mValues.get(position);
		holder.mNameView.setText(mValues.get(position).name);
		String price = mValues.get(position).price + " " + mValues.get(position).currency;
		holder.mPriceView.setText(price);

		holder.mImageView.setBackgroundColor(mValues.get(position).imageColor);

		holder.itemView.setTag(mValues.get(position));
		holder.itemView.setOnClickListener(mOnClickListener);

		if (ProductCart.ITEM_MAP.containsKey(holder.mItem.sku)) {
			holder.mLabel.setText(R.string.label_in_cart);
		} else {
			holder.mLabel.setText(R.string.label_add);
		}
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

	public static class ProductViewHolder extends RecyclerView.ViewHolder {
		public final View mView;
		public final ImageView mImageView;
		public final TextView mNameView;
		public final TextView mPriceView;
		public final TextView mLabel;
		public ProductContent.ProductItem mItem;

		public ProductViewHolder(final View view) {
			super(view);
			mView = view;
			mImageView = (ImageView) view.findViewById(R.id.item_image);
			mNameView = (TextView) view.findViewById(R.id.name);
			mPriceView = (TextView) view.findViewById(R.id.price);
			mLabel = (TextView) view.findViewById(R.id.label);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mNameView.getText() + "'";
		}
	}
}
