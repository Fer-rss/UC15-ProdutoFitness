package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "ProdutofitPU";

    // Singleton thread-safe da factory
    private static EntityManagerFactory factory;

    // Retorna um novo EntityManager SEMPRE
    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            synchronized (JPAUtil.class) {
                if (factory == null || !factory.isOpen()) {
                    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
                }
            }
        }
        return factory.createEntityManager();
    }

    // Fecha a fábrica ao encerrar o sistema
    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
