package org.enoch.snark;

import org.enoch.snark.instance.Universe;
import org.hibernate.Session;

import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Main {

    public static final String DATA_DIR_NAME = "data/";

    public static void main(String[] args) throws IOException {

        String sql = "select * from universes";
        final Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
//        String sql = "select version()";

        final List list = session.createNativeQuery(sql).list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();


        HibernateUtils.shutdown();
//        Query query = session.createQuery("from Universe");
//        final List resultList = query.getResultList();


        final File data = new File(DATA_DIR_NAME);
        for(File accountDir : Objects.requireNonNull(data.listFiles())) {
            if(accountDir.isDirectory()){
                Runnable task = new Universe(accountDir.getAbsolutePath())::runSI;
                new Thread(task).start();
            }
        }

    }
}
