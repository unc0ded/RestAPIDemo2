package com.unc0ded.restapidemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unc0ded.restapidemo2.models.Person;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonVH> {

    private ArrayList<Person> list;

    public PersonAdapter(ArrayList<Person> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PersonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonVH holder, int position) {
        holder.populate(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonVH extends RecyclerView.ViewHolder {

        TextView age,number,name,id;
        public PersonVH(@NonNull View itemView) {
            super(itemView);
            age=itemView.findViewById(R.id.age);
            name=itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.id);
            number=itemView.findViewById(R.id.number);
        }

        public void populate(Person person) {
            name.setText(person.getName());
            number.setText("+91 "+person.getNumber());
            age.setText(person.getAge()+" yrs");
            id.setText(person.getId());
        }
    }
}
