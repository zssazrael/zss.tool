package zss.tool;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Version("2018.05.17")
public class Strings {
    private static final Map<Class<?>, Strings> INSTANCES = new HashMap<>();
    private final Map<String, String> strings = new HashMap<>();

    public String getString(final String name) {
        return StringUtils.defaultString(strings.get(name));
    }

    public void load(final Class<?> type) {
        final String resourceName = type.getSimpleName().concat(".strings.xml");
        final InputStream stream = type.getResourceAsStream(resourceName);
        if (stream == null) {
            return;
        }
        try {
            load(stream);
        } finally {
            IOTool.close(stream);
        }
    }

    public void load(final InputStream stream) {
        final Document document = XMLTool.newDocument(stream);
        load(document);
    }

    public void load(final Document document) {
        synchronized (strings) {
            final Element rootElement = document.getDocumentElement();
            for (Element element : XMLTool.getChildElements(rootElement)) {
                final String name = element.getAttribute("name");
                final String content = element.getTextContent().trim();
                strings.put(name, content);
            }
        }
    }

    public static Strings getStrings(final Class<?> type) {
        synchronized (INSTANCES) {
            Strings strings = INSTANCES.get(type);
            if (strings == null) {
                strings = new Strings();
                strings.load(type);
                INSTANCES.put(type, strings);
            }
            return strings;
        }
    }
}
