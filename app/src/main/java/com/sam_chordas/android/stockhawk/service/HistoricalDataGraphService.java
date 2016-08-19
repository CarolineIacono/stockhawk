package com.sam_chordas.android.stockhawk.service;//package com.sam_chordas.android.stockhawk.service;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.OperationApplicationException;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.os.RemoteException;
//import android.util.Log;
//
//import com.google.android.gms.gcm.GcmNetworkManager;
//import com.google.android.gms.gcm.GcmTaskService;
//import com.google.android.gms.gcm.TaskParams;
//import com.sam_chordas.android.stockhawk.data.QuoteColumns;
//import com.sam_chordas.android.stockhawk.data.QuoteProvider;
//import com.sam_chordas.android.stockhawk.rest.Utils;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
///**
// * Created by carolinestewart on 8/16/16.
// */

public class HistoricalDataGraphService  {}
//
//    Context context;
//
//    public HistoricalDataGraphService() {
//    }
//
//    public HistoricalDataGraphService(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public int onRunTask(TaskParams params) {
//        Cursor initQueryCursor;
//        if (context == null) {
//            context = this;
//        }
//        StringBuilder urlStringBuilder = new StringBuilder();
//        try {
//            // Base URL for the Yahoo query
//            urlStringBuilder.append("https://query.yahooapis.com/v1/public/yql?q=");
//            urlStringBuilder.append(URLEncoder.encode("select * from yahoo.finance.quotes where symbol "
//                    + "in (", "UTF-8"));
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
////        }
////        if ()
////
////
////        // finalize the URL for the API query.
////        urlStringBuilder.append("&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables."
////                + "org%2Falltableswithkeys&callback=");
////
////        String urlString;
////        String getResponse;
////        int result = GcmNetworkManager.RESULT_FAILURE;
////
////        if (urlStringBuilder != null) {
////            urlString = urlStringBuilder.toString();
////            try {
////                getResponse = fetchData(urlString);
////                result = GcmNetworkManager.RESULT_SUCCESS;
////                try {
////                    ContentValues contentValues = new ContentValues();
////                    // update ISCURRENT to 0 (false) so new data is current
////                    if (isUpdate) {
////                        contentValues.put(QuoteColumns.ISCURRENT, 0);
////                        context.getContentResolver().update(QuoteProvider.Quotes.CONTENT_URI, contentValues,
////                                null, null);
////                    }
////                    context.getContentResolver().applyBatch(QuoteProvider.AUTHORITY,
////                            Utils.quoteJsonToContentVals(getResponse));
////                } catch (RemoteException | OperationApplicationException e) {
////                    Log.e(LOG_TAG, "Error applying batch insert", e);
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
//////            }
//////        }
////
////        return result;
////    }
////
////}
//
