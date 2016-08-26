package com.caroline.android.stockhawk.data;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;

/**
 * Created by carolinestewart on 8/24/16.
 */
public class HistoricalQuote {


    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String NAME = "name";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String CURRENCY = "currency";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String LASTTRADEDATE = "lasttradedate";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DAYLOW = "daylow";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String DAYHIGH = "dayhigh";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String YEARLOW = "yearlow";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String YEARHIGH = "yearhigh";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String EARNINGSSHARE = "earningsshare";
    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String MARKETCAPITALIZATION = "marketcapitalization";
}
