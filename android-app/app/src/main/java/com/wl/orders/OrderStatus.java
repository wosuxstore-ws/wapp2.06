package com.wl.orders;

import androidx.annotation.ColorRes;

// Everything the Orders screens need to know about the `orders.status` values:
//   pending / paid -> processing -> shipped -> delivered      (+ cancelled)
// "pending" and "paid" both show as "Placed".
final class OrderStatus {

    private OrderStatus() {}

    static final String[] STEP_LABELS = {"Placed", "Processing", "Shipped", "Delivered"};

    // 0..3 = position on the stepper, -1 = cancelled (no stepper)
    static int step(String status) {
        if (status == null) return 0;
        switch (status) {
            case "processing": return 1;
            case "shipped":    return 2;
            case "delivered":  return 3;
            case "cancelled":  return -1;
            default:           return 0; // pending, paid, anything unknown
        }
    }

    // The status the big bottom button moves the order to (null = nothing left to do).
    static String next(String status) {
        switch (step(status)) {
            case 0:  return "processing";
            case 1:  return "shipped";
            case 2:  return "delivered";
            default: return null;
        }
    }

    static String nextButtonLabel(String status) {
        switch (step(status)) {
            case 0:  return "Move to Processing";
            case 1:  return "Move to Shipped";
            case 2:  return "Mark as Delivered";
            default: return "";
        }
    }

    // Key used by the filter chips on the Orders list.
    static String filterKey(String status) {
        switch (step(status)) {
            case 1:  return "processing";
            case 2:  return "shipped";
            case 3:  return "delivered";
            case -1: return "cancelled";
            default: return "placed";
        }
    }

    @ColorRes static int pillBg(String status) {
        switch (step(status)) {
            case 1:  return R.color.pill_processing_bg;
            case 2:  return R.color.pill_shipped_bg;
            case 3:  return R.color.pill_delivered_bg;
            case -1: return R.color.pill_cancelled_bg;
            default: return R.color.pill_placed_bg;
        }
    }

    @ColorRes static int pillFg(String status) {
        switch (step(status)) {
            case 1:  return R.color.pill_processing_fg;
            case 2:  return R.color.pill_shipped_fg;
            case 3:  return R.color.pill_delivered_fg;
            case -1: return R.color.pill_cancelled_fg;
            default: return R.color.pill_placed_fg;
        }
    }
}
