package com.engelsiz.engelsiz_baston2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

  private LayoutInflater elementsInflatter;
  private List<Listing> elementListing;
  View rowView;
  TextView elemets_text;
  Listing elemets;

  public CustomAdapter(Activity activity, List<Listing> elementListing) {
    elementsInflatter = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.elementListing = elementListing;
  }


  @Override
  public int getCount() {
    return elementListing.size();
  }

  @Override
  public Object getItem(int position) {
    return elementListing.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    rowView = elementsInflatter.inflate(R.layout.row_layout, null);
    elemets_text = rowView.findViewById(R.id.tvNewspaper);

    elemets = elementListing.get(position);
    elemets_text.setText(elemets.getElement());
    elemets.setElement(elemets.getElement());

    return rowView;
  }
}
