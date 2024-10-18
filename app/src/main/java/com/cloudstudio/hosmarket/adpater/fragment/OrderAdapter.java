package com.cloudstudio.hosmarket.adpater.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudstudio.hosmarket.R;
import com.cloudstudio.hosmarket.adpater.ImageItemAdapter;
import com.cloudstudio.hosmarket.entity.BookBean;
import com.cloudstudio.hosmarket.entity.OrderBean;

import java.util.List;

/**
 * @ClassName OrderAdapter
 * @Author Create By matrix
 * @Date 2024/10/18 0:24
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private List<OrderBean> orderBeanList;
    private Activity mActivity;

    public OrderAdapter(Activity mActivity, List<OrderBean> list){
        this.orderBeanList=list;
        this.mActivity=mActivity;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_view,null);
        OrderAdapter.ViewHolder viewHolder=new OrderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.item_name.setText(orderBeanList.get(position).getMedicine_name());
        holder.item_price.setText("￥"+orderBeanList.get(position).getOrder_amount());
        holder.item_quantity.setText("x"+orderBeanList.get(position).getOrder_quantity());
        holder.item_time.setText(orderBeanList.get(position).getOrder_time());
    }

    @Override
    public int getItemCount() {
        return orderBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_name;
        private final TextView item_price;
        private final TextView item_quantity;
        private final TextView item_time;
        public ViewHolder(View itemView) {
            super(itemView);
            item_name=itemView.findViewById(R.id.item_name);
            item_price=itemView.findViewById(R.id.item_price);
            item_quantity=itemView.findViewById(R.id.item_quantity);
            item_time=itemView.findViewById(R.id.item_time);
        }
    }
}
