package fr.supinternet.slike;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SYLVAIN on 26/09/2017.
 */


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;

    private ArrayList<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(0, message);
        notifyDataSetChanged();

    }

    public void removeMessage(Message message) {
        messages.remove(message);
        notifyDataSetChanged();

    }

    //bug ici
    public Adapter(Context context, ArrayList<Message> messagesList) {
        this.context = context;
        this.messages = messagesList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //test
        Message message = messages.get(position);
        Log.d("msg for position : " + position, message + "");
        // TO DO : depuis mainAct2 envoyer la list des messages de firebase
        // ICI setText, mais regarder la position (si c'est null ca crash une erreur ...)
        /*
        holder.tvName.setText(message.getUser());
        holder.tvMessage.setText(message.getUser());
        */

        //test
        //ICI ERREUR
        if (message != null) {
            holder.tvName.setText(message.getUser());
            holder.tvMessage.setText(message.getUser());

        }else{
            Log.d("message debug", "MSG IS NULL");
        }

    }


    @Override
    public int getItemCount() {
        //return (null != messages ? messages.size() : 0);
        return messages.size();

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }

        public TextView getTvMessage() {
            return tvMessage;

        }

        public void setTvMessage(TextView tvMessage) {
            this.tvMessage = tvMessage;
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }


    }
}