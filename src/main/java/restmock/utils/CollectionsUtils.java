package restmock.utils;

import java.util.Collection;

/**
 * Created by lsz on 2015/12/9.
 */
public class CollectionsUtils {
    public static boolean isEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }
    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }
}