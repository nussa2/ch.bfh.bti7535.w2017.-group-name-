package ch.bfh.bti7535.w2017.groupname.process;

/**
 * Interface für alle ChainProcessors
 */
public interface ChainProcessor {

    /**
     * Prozessiert die mitgegebene Konfiguration
     *
     * @param chain
     * @throws Exception
     */
    void process(ProcessChainConfiguration chain) throws Exception;
}
