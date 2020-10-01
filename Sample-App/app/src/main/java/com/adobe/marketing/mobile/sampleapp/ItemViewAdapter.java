/*
 * Copyright 2019 Adobe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

	public class ProductViewHolder extends RecyclerView.ViewHolder {
		public final View mView;
		public final ImageView mImageView;
		public final TextView mNameView;
		public final TextView mPriceView;
		public ProductContent.ProductItem mItem;

		public ProductViewHolder(final View view) {
			super(view);
			mView = view;
			mImageView = (ImageView) view.findViewById(R.id.item_image);
			mNameView = (TextView) view.findViewById(R.id.name);
			mPriceView = (TextView) view.findViewById(R.id.price);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mNameView.getText() + "'";
		}
	}
}
