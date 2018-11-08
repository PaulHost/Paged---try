package ph.hostev.paul.paginglibrarytry.util;

import androidx.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ph.hostev.paul.paginglibrarytry.model.Model;

public final class MockUtil {
    private static final String TAG = MockUtil.class.getCanonicalName();
    private static final int SLEEP = 3000;

    @NonNull
    public static List<Model> mockPageKeyed(int page) {
        List<Model> models = new ArrayList<>();
        sleepThread();
        for (int i = 1; i < 101; i++) {
            models.add(new Model(Integer.parseInt("" + page + i), "page=" + page + " item=" + i));
        }
        return models;
    }

    @NonNull
    public static List<Model> mockPositional(int position, int loadSize, int total) {
        List<Model> models = new ArrayList<>();
        sleepThread();
        int gap = position + loadSize;
        for (; position < gap && position < total; position++) {
            models.add(new Model(position, "total=" + total));
        }
        Log.d(TAG, "position=" + position +
                " loadSize=" + loadSize +
                " total=" + total +
                " modelsSize=" + models.size()
        );
        return models;
    }

    @NonNull
    public static List<Model> mockItemKeyedAfter(Integer key, int requestedLoadSize) {
        List<Model> models = new ArrayList<>();
        sleepThread();
        for (; models.size() < requestedLoadSize && key < 501; key++) {
            models.add(new Model(key, " item=" + key));
        }
        Log.d(TAG, "nextKey=" + key +
                " modelsListSize=" + models.size() +
                " requestedLoadSize=" + requestedLoadSize +
                (models.size() > 1 ? " lastKey=" + models.get(models.size() - 1).getKey() : "")
        );
        return models;
    }

    @NonNull
    public static List<Model> mockItemKeyedBefore(Integer key, int requestedLoadSize) {
        List<Model> models = new ArrayList<>();
        for (; key > 1 && models.size() < requestedLoadSize + 1; key--) {
            models.add(new Model(key, " item=" + key));
        }
        Log.d(TAG, "prevKey=" + key +
                " modelsListSize=" + models.size() +
                " requestedLoadSize=" + requestedLoadSize +
                (models.size() > 1 ? " lastKey=" + models.get(models.size() - 1).getKey() : "")
        );
        Collections.reverse(models);
        return models;
    }

    private static void sleepThread() {
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
