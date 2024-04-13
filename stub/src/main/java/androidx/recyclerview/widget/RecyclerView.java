package androidx.recyclerview.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerView extends ViewGroup {
    public RecyclerView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    // LayoutManager
    public static class m {

    }

    // ViewHolder
    public static abstract class c0 {
        public c0(View view) {
        }
    }

    // Adapter
    public static abstract class e<VH extends c0> {
        public abstract int k();
        public abstract c0 I(int i, RecyclerView recyclerView);
        public abstract void F(VH vh, int i);
    }

    public void setLayoutManager(m m) {

    }

    public void setAdapter(e eVar) {

    }
}
