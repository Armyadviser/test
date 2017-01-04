package storm_falcon.util.xls;

/**
 * Created by Storm_Falcon on 2016/12/5.
 * Map cell contents to an object;
 */
public interface CellMapper<T> {
    T convert(String... items);
}
