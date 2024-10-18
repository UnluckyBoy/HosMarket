package com.cloudstudio.hosmarket.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudstudio.hosmarket.R;
import com.cloudstudio.hosmarket.adpater.fragment.OrderAdapter;
import com.cloudstudio.hosmarket.entity.OrderBean;
import com.cloudstudio.hosmarket.entity.WebServerResponseBean;
import com.cloudstudio.hosmarket.entity.WebServerResponseOrderBean;
import com.cloudstudio.hosmarket.network.api.AddWareHouseApi;
import com.cloudstudio.hosmarket.network.api.OrderApi;
import com.cloudstudio.hosmarket.network.api.OutWareHouseApi;
import com.cloudstudio.hosmarket.network.api.QueryMedicineApi;
import com.cloudstudio.hosmarket.network.service.AddWareHouseService;
import com.cloudstudio.hosmarket.network.service.OrderService;
import com.cloudstudio.hosmarket.network.service.OutWareHouseService;
import com.cloudstudio.hosmarket.network.service.QueryMedicineService;
import com.cloudstudio.hosmarket.util.StringUtil;
import com.cloudstudio.hosmarket.util.UUIDNumberUtil;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName CommonFragment
 * @Author Create By matrix
 * @Date 2024/10/16 22:16
 */
public class CommonFragment extends Fragment {
    private int layoutResId;
    private String parentType;

    private View root;

    private PercentRelativeLayout wareHouseManagerLay,orderManagerLay;
    RecyclerView orderRecyclerView;

    List<OrderBean> orderList;

    private TextView codeText;
    private TextView nameText;
    private TextView createTimeText;
    private TextView batchText;
    private TextView priceText;
    private TextView retailText;
    private TextView countText;

    private String orderUid="";

    public static CommonFragment newInstance(int layoutResId,String parentType) {
        CommonFragment fragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putInt("layoutResId", layoutResId);
        args.putString("parentType", parentType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 从参数中获取布局资源ID
        if (getArguments() != null) {
            layoutResId = getArguments().getInt("layoutResId");
            parentType = getArguments().getString("parentType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root=inflater.inflate(layoutResId, container, false);
        // 根据布局资源ID加载布局
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        TextView fragmentTitle=root.findViewById(R.id.fragmentTitle);
        switch (parentType){
            case "toWareHouse":
                fragmentTitle.setText(R.string.toWareHouseTitle);
                break;
            case"outWareHouse":
                fragmentTitle.setText(R.string.outWareHouseTitle);
                break;
            case"order":
                fragmentTitle.setText(R.string.orderTitle);
                orderRecyclerView=root.findViewById(R.id.orderListView);
                orderRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                orderList = new ArrayList<>();
                //orderUid= UUIDNumberUtil.randUUIDNumberAndTime();
                break;
        }
    }

    private void initView(View view){
        Button scanBtnView = view.findViewById(R.id.scanBtn);
        wareHouseManagerLay=view.findViewById(R.id.wareHouseManagerLay);
        orderManagerLay=view.findViewById(R.id.orderManagerLay);

        wareHouseManagerLay.setVisibility(View.GONE);
        orderManagerLay.setVisibility(View.GONE);
        scanBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * 启动扫码
                 */
                ScanOptions options = new ScanOptions();
                options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES);
                options.setPrompt("扫描条形码");
                options.setCameraId(0);  // Use a specific camera of the device
                options.setBeepEnabled(false);
                options.setBarcodeImageEnabled(true);
                barcodeLauncher.launch(options);
            }
        });
    }

    /**
     * 返回扫码结果
     */
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(getActivity(), "取消扫码", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    bindView(result.getContents());
                }
            });

    private void bindView(String data){
        codeText = root.findViewById(R.id.codeText);
        nameText=root.findViewById(R.id.nameText);
        batchText = root.findViewById(R.id.batchText);
        createTimeText = root.findViewById(R.id.createTimeText);
        priceText=root.findViewById(R.id.priceText);
        retailText=root.findViewById(R.id.retailText);
        countText=root.findViewById(R.id.countText);
        TextView countViewLabel = root.findViewById(R.id.countViewLabel);
        Button wareHouseBtn = root.findViewById(R.id.wareHouseBtn);
        switch (parentType){
            case "toWareHouse":
                countViewLabel.setText(R.string.toHouseCountViewLabel);
                wareHouseBtn.setText(R.string.toWareHouseBtn);
                QueryMedicineApi queryUserInfoApi=new QueryMedicineApi();
                QueryMedicineService queryMedicineService=queryUserInfoApi.getService();
                Call<WebServerResponseBean> queryInfoCall=queryMedicineService.getState(data.substring(0,6));
                queryInfoCall.enqueue(new Callback<WebServerResponseBean>() {
                    @Override
                    public void onResponse(Call<WebServerResponseBean> call, Response<WebServerResponseBean> response) {
                        if(response.body().getHandleCode()==200){
                            wareHouseManagerLay.setVisibility(View.VISIBLE);
                            codeText.setText(data.substring(0,6));
                            nameText.setText(response.body().getHandleData().getMedicine_name());
                            priceText.setText(String.valueOf(response.body().getHandleData().getMedicine_price()));
                            retailText.setText(String.valueOf(response.body().getHandleData().getMedicine_retail()));
                            createTimeText.setText(StringUtil.dateFormat(data.substring(6,14)));
                            batchText.setText(data.substring(14,18));
                            countText.setText("");
                        }else{
                            Toast.makeText(getActivity(),"查询异常",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WebServerResponseBean> call, Throwable throwable) {
                        Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "outWareHouse":
                countViewLabel.setText(R.string.outHouseCountViewLabel);
                wareHouseBtn.setText(R.string.outWareHouseBtn);
                QueryMedicineApi outWareQueryUserInfoApi=new QueryMedicineApi();
                QueryMedicineService outWareQueryMedicineService=outWareQueryUserInfoApi.getService();
                Call<WebServerResponseBean> outWareQueryInfoCall=outWareQueryMedicineService.getState(data.substring(0,6));
                outWareQueryInfoCall.enqueue(new Callback<WebServerResponseBean>() {
                    @Override
                    public void onResponse(Call<WebServerResponseBean> call, Response<WebServerResponseBean> response) {
                        if(response.body().getHandleCode()==200){
                            wareHouseManagerLay.setVisibility(View.VISIBLE);
                            codeText.setText(data.substring(0,6));
                            nameText.setText(response.body().getHandleData().getMedicine_name());
                            priceText.setText(String.valueOf(response.body().getHandleData().getMedicine_price()));
                            retailText.setText(String.valueOf(response.body().getHandleData().getMedicine_retail()));
                            createTimeText.setText(StringUtil.dateFormat(data.substring(6,14)));
                            batchText.setText(data.substring(14,18));
                            countText.setText("");
                        }else{
                            Toast.makeText(getActivity(),"查询异常",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WebServerResponseBean> call, Throwable throwable) {
                        Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "order":
                orderManagerLay.setVisibility(View.VISIBLE);
                orderHandle(data);
                break;
        }

        wareHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!StringUtil.isEmptyStr(countText.getText().toString())){
                    switch (parentType){
                        case "toWareHouse":
                            AddWareHouseApi addWareHouseApi=new AddWareHouseApi();
                            AddWareHouseService addWareHouseService=addWareHouseApi.getService();
                            Call<WebServerResponseBean> addWareCall=addWareHouseService.getState(codeText.getText().toString(),
                                    Integer.parseInt(batchText.getText().toString()),
                                    Integer.parseInt(countText.getText().toString()),
                                    StringUtil.stringFormat(createTimeText.getText().toString()));
                            addWareCall.enqueue(new Callback<WebServerResponseBean>() {
                                @Override
                                public void onResponse(Call<WebServerResponseBean> call, Response<WebServerResponseBean> response) {
                                    if(response.body().getHandleCode()==200){
                                        Toast.makeText(getActivity(),nameText.getText().toString()+response.body().getHandleMessage(),Toast.LENGTH_SHORT).show();
                                        clearAllElement();
                                    }else{
                                        Toast.makeText(getActivity(),nameText.getText().toString()+response.body().getHandleMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<WebServerResponseBean> call, Throwable throwable) {
                                    Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        case "outWareHouse":
                            OutWareHouseApi outWareHouseApi=new OutWareHouseApi();
                            OutWareHouseService outWareHouseService=outWareHouseApi.getService();
                            Call<WebServerResponseBean> outWareCall=outWareHouseService.getState(codeText.getText().toString(),
                                    Integer.parseInt(batchText.getText().toString()),
                                    Integer.parseInt(countText.getText().toString()),
                                    StringUtil.stringFormat(createTimeText.getText().toString()));
                            outWareCall.enqueue(new Callback<WebServerResponseBean>() {
                                @Override
                                public void onResponse(Call<WebServerResponseBean> call, Response<WebServerResponseBean> response) {
                                    if(response.body().getHandleCode()==200){
                                        Toast.makeText(getActivity(),nameText.getText().toString()+response.body().getHandleMessage(),Toast.LENGTH_SHORT).show();
                                        clearAllElement();
                                    }else{
                                        Toast.makeText(getActivity(),nameText.getText().toString()+response.body().getHandleMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<WebServerResponseBean> call, Throwable throwable) {
                                    Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                    }
                }else {
                    Toast.makeText(getActivity(),"请输入库存数!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 清空出入库的视图逻辑
     */
    private void clearAllElement(){
        codeText.setText("");
        nameText.setText("");
        createTimeText.setText("");
        batchText.setText("");
        priceText.setText("");
        retailText.setText("");
        countText.setText("");
        wareHouseManagerLay.setVisibility(View.GONE);
    }

    private void orderHandle(String data){
        Button settleBtn=root.findViewById(R.id.settleBtn);
        Button cancelBtn=root.findViewById(R.id.cancelBtn);
        if(StringUtil.isEmptyStr(orderUid)){
            orderUid= UUIDNumberUtil.randUUIDNumberAndTime();
        }

        OrderApi orderApi=new OrderApi();
        OrderService orderService=orderApi.getService();
        Call<WebServerResponseOrderBean> orderCall=orderService.getState(orderUid,data.substring(0,6),
                Integer.parseInt(data.substring(14,18)),data.substring(6,14));
        orderCall.enqueue(new Callback<WebServerResponseOrderBean>() {
            @Override
            public void onResponse(Call<WebServerResponseOrderBean> call, Response<WebServerResponseOrderBean> response) {
                if(response.body().getHandleCode()==200){
                    orderList.add(response.body().getHandleData());
                    OrderAdapter adapter = new OrderAdapter(getActivity(), orderList);
                    orderRecyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(),nameText.getText().toString()+response.body().getHandleMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WebServerResponseOrderBean> call, Throwable throwable) {
                Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
