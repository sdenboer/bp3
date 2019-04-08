package com.example.bp3.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.bp3.R;
import com.example.bp3.service.models.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sven
 */
public class TagSearchAdapter extends ArrayAdapter<Tag> {
    private List<Tag> tagList;

    public TagSearchAdapter(Context context, List<Tag> tags) {
        super(context, 0, tags);
        tagList = new ArrayList<>(tags);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return tagFilter;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_tag_autocomplete, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.card_tag_ac_text);

        Tag tag = getItem(position);

        if (tag != null) {
            textView.setText(tag.getTag());
        }

        return convertView;

    }

    public void setTags(List<Tag> t) {
        this.tagList = t;
        notifyDataSetChanged();
    }

    private Filter tagFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Tag> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(tagList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Tag tag : tagList) {
                    if (tag.getTag().toLowerCase().contains(filterPattern)) {
                        suggestions.add(tag);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Tag) resultValue).getTag();
        }
    };
}
