package org.openhab.habdroid.util;

/**
 * Created by tamon on 18.03.15.
 */
public class SharedConstants {

    public enum OpenHabVersion {
        ONE, TWO;
    }

    public enum DataMapKey {
        SITEMAP_NAME, SITEMAP_LINK, SITEMAP_XML, OPENHAB_VERSION;
    }

    public enum DataMapUrl {
        SITEMAP_BASE, SITEMAP_DETAILS, OPENHAB_VERSION;

        public String value() {
            return "/" + name();
        }
    }

    public enum MessagePath {
        LOAD_SITEMAP, SEND_TO_OPENHAB, SUCCESS;

        public String value() {
            return "/" + name();
        }
    }

    public enum ObserverData {
        CONNECTED, CONNECTION_SUSPENDED;
    }
}
