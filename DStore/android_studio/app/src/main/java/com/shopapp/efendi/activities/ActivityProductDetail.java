package com.shopapp.efendi.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.shopapp.efendi.Config;
import com.shopapp.efendi.R;
import com.shopapp.efendi.utilities.DBHelper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import static com.shopapp.efendi.utilities.Constant.GET_TAX_CURRENCY;

public class ActivityProductDetail extends AppCompatActivity {
/* актифити с инфой по продукту и возможностью заказа */
    long product_id;
    TextView txt_product_name, txt_product_price, txt_product_quantity, txt_ram, txt_hd, txt_processor, txt_video, txt_display;
    private String product_name, product_image, category_name, product_status, currency_code, product_description, ram, processor, video, hd, display;
    private double product_price;
    private int product_quantity;
    WebView txt_product_description;
    ImageView img_product_image;
    Button btn_cart;
    public static DBHelper dbhelper;
    final Context context = this;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    double resp_tax;
    String resp_currency_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        dbhelper = new DBHelper(this);
        getData();//Получаем данные переданные из фрагмента с пк
        initComponent();//подключаем переменные к вьюхам
        displayData();//вводим полученные данные в текстовые поля
        setupToolbar();
        makeJsonObjectRequest();
    }

    public void setupToolbar() {

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(category_name);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        product_id = intent.getLongExtra("product_id", 0);
        product_name = intent.getStringExtra("title");
        product_image = intent.getStringExtra("image");
        product_price = intent.getDoubleExtra("product_price", 0);
        product_description = intent.getStringExtra("product_description");
        product_quantity = intent.getIntExtra("product_quantity", 0);
        product_status = intent.getStringExtra("product_status");
        currency_code = intent.getStringExtra("currency_code");
        category_name = intent.getStringExtra("category_name");
        ram = intent.getStringExtra("ram");
        hd = intent.getStringExtra("hd");
        processor = intent.getStringExtra("processor");
        video = intent.getStringExtra("video");
        display = intent.getStringExtra("display");
    }

    public void initComponent() {
        txt_product_name = findViewById(R.id.product_name);
        img_product_image = findViewById(R.id.product_image);
        txt_product_price = findViewById(R.id.product_price);
        txt_product_description = findViewById(R.id.product_description);
        txt_product_quantity = findViewById(R.id.product_quantity);
        btn_cart = findViewById(R.id.btn_add_cart);
        txt_ram = findViewById(R.id.txt_ram);
        txt_processor = findViewById(R.id.txt_processor);
        txt_video = findViewById(R.id.txt_video);
        txt_hd = findViewById(R.id.txt_hd);
        txt_display = findViewById(R.id.txt_display);

    }

    public void displayData() {
        txt_product_name.setText(product_name);
        txt_ram.setText("RAM:\t" + ram);
        txt_processor.setText("Processor:\t" + processor);
        txt_video.setText("VideoCard:\t" + video);
        txt_hd.setText("Hard Disk:\t" + hd);
        txt_display.setText("Display:\t" + display);



        Picasso.with(this)
                .load(Config.ADMIN_PANEL_URL + "/upload/product/" + product_image.replace(" ", "%20"))
                .placeholder(R.drawable.ic_loading)
                .into(img_product_image);

        img_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityImageDetail.class);
                intent.putExtra("image", product_image);
                startActivity(intent);
            }
        });


        String price = String.format(Locale.GERMAN, "%1$,.0f", product_price);
        txt_product_price.setText(price + " " + currency_code);


        txt_product_quantity.setText(product_quantity + " " + getString(R.string.txt_items));

        if (product_status.equals("Available")) {
            btn_cart.setText(R.string.btn_add_to_cart);
            btn_cart.setBackgroundResource(R.color.available);
            btn_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputDialog();
                }
            });
        } else {
            btn_cart.setEnabled(false);
            btn_cart.setText(R.string.btn_out_of_stock);
            btn_cart.setBackgroundResource(R.color.sold);
        }

        txt_product_description.setBackgroundColor(Color.parseColor("#ffffff"));
        txt_product_description.setFocusableInTouchMode(false);
        txt_product_description.setFocusable(false);
        txt_product_description.getSettings().setDefaultTextEncodingName("UTF-8");

        WebSettings webSettings = txt_product_description.getSettings();
        Resources res = getResources();
        int fontSize = res.getInteger(R.integer.font_size);
        webSettings.setDefaultFontSize(fontSize);
        webSettings.setJavaScriptEnabled(true);

        String mimeType = "text/html; charset=UTF-8";
        String encoding = "utf-8";
        String htmlText = product_description;

        String text = "<html><head>"
                + "<style type=\"text/css\">body{color: #525252;}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        String text_rtl = "<html dir='rtl'><head>"
                + "<style type=\"text/css\">body{color: #525252;}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";


            txt_product_description.loadDataWithBaseURL(null, text, mimeType, encoding, null);


    }

    public void inputDialog() {

        try {
            dbhelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);

        View mView = layoutInflaterAndroid.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(mView);

        final EditText edtQuantity = (EditText) mView.findViewById(R.id.userInputDialog);
        alert.setCancelable(false);
        int maxLength = 3;
        edtQuantity.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        edtQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setPositiveButton(R.string.dialog_option_add, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String temp = edtQuantity.getText().toString();
                int quantity = 0;

                if (!temp.equalsIgnoreCase("")) {

                    quantity = Integer.parseInt(temp);

                    if (quantity <= 0) {
                        Toast.makeText(getApplicationContext(), R.string.msg_stock_below_0, Toast.LENGTH_SHORT).show();
                    } else if (quantity > product_quantity) {
                        Toast.makeText(getApplicationContext(), R.string.msg_stock_not_enough, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.msg_success_add_cart, Toast.LENGTH_SHORT).show();

                        if (dbhelper.isDataExist(product_id)) {
                            dbhelper.updateData(product_id, quantity, (product_price * quantity));
                        } else {
                            dbhelper.addData(product_id, product_name, quantity, (product_price * quantity), currency_code, product_image/*, ram, processor, video, display, hd*/);
                        }
                    }

                } else {
                    dialog.cancel();
                }
            }
        });

        alert.setNegativeButton(R.string.dialog_option_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

    private void makeJsonObjectRequest() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, GET_TAX_CURRENCY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("INFO", response.toString());
                try {
                    resp_tax = response.getDouble("tax");
                    resp_currency_code = response.getString("currency_code");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("INFO", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.cart:
                Intent intent = new Intent(getApplicationContext(), ActivityCart.class);
                intent.putExtra("tax", resp_tax);
                intent.putExtra("currency_code", resp_currency_code);
                startActivity(intent);
                break;

            case R.id.share:
                requestStoragePermission();
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @TargetApi(16)
    private void requestStoragePermission() {
        Dexter.withActivity(ActivityProductDetail.this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            (new ShareTask(ActivityProductDetail.this)).execute(Config.ADMIN_PANEL_URL + "/upload/product/" + product_image);
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityProductDetail.this);
        builder.setTitle(R.string.permission_msg);
        builder.setMessage(R.string.permission_storage);
        builder.setPositiveButton(R.string.dialog_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton(R.string.dialog_option_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    public class ShareTask extends AsyncTask<String, String, String> {
        private Context context;
        private ProgressDialog pDialog;
        URL myFileUrl;
        Bitmap bmImg = null;
        File file;

        public ShareTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage(getString(R.string.loading_msg));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                myFileUrl = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String path = myFileUrl.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                File filepath = Environment.getExternalStorageDirectory();
                File dir = new File(filepath.getAbsolutePath() + "/" + getResources().getString(R.string.app_name) + "/");
                dir.mkdirs();
                String fileName = idStr;
                file = new File(dir, fileName);
                FileOutputStream fos = new FileOutputStream(file);
                bmImg.compress(Bitmap.CompressFormat.PNG, 99, fos);
                fos.flush();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String args) {

            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_product_section_one) + " " + product_name + " " + getString(R.string.share_product_section_two) + " " + String.format(Locale.GERMAN, "%1$,.0f", product_price) + " " + currency_code + getString(R.string.share_product_section_three) + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(intent, "Share Image"));
            pDialog.dismiss();

        }
    }

}