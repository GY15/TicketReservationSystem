package web.dao.daoImpl;

import web.dao.StoreDao;
import web.model.Store;

public class StoreDaoImpl extends BaseDaoImpl implements StoreDao {

    public void saveStore(Store store) {
        super.save(store);
    }

    public void deleteStore(Store store) {

    }

    public Store getStoreByID(String id) {
        return null;
    }


}
