package zss.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Version("2015-03-17")
public final class XMLTool
{
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLTool.class);

    private static final String EXCEPTION_MESSAGE_NEW_DOCUMENT_BUILDER_FACTORY = "Invoke [newDocumentBuilderFactory] failed!";

    public static Map<String, List<Element>> getElementsByTagNames(final Element root, final String... names)
    {
        Map<String, List<Element>> map = new HashMap<>();
        for (String name : names)
        {
            map.put(name, new LinkedList<Element>());
        }
        Node node = root.getFirstChild();
        while (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                List<Element> elements = map.get(element.getTagName());
                if (elements != null)
                {
                    elements.add(element);
                }
            }
            node = node.getNextSibling();
        }
        return map;
    }

    public static Element getElementByTagName(final Element root, final String name)
    {
        Node node = root.getFirstChild();
        while (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                if (element.getTagName().equals(name))
                {
                    return element;
                }
            }
            node = node.getNextSibling();
        }
        return null;
    }

    public static List<Element> getElementsByTagName(final Element root, final String name)
    {
        List<Element> elements = new LinkedList<>();
        Node node = root.getFirstChild();
        while (node != null)
        {
            if (node instanceof Element)
            {
                Element element = (Element) node;
                if (element.getTagName().equals(name))
                {
                    elements.add(element);
                }
            }
            node = node.getNextSibling();
        }
        return elements;
    }

    public static List<Element> getChildElements(final Element root)
    {
        List<Element> elements = new LinkedList<>();
        Node node = root.getFirstChild();
        while (node != null)
        {
            if (node instanceof Element)
            {
                elements.add((Element) node);
            }
            node = node.getNextSibling();
        }
        return elements;
    }

    public static Document newDocument(final String path)
    {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        InputStream stream;
        try
        {
            stream = url.openStream();
        }
        catch (IOException e)
        {
            LOGGER.error("Invoke [newDocument] failed!", e);
            throw new LoggedException("Invoke [newDocument] failed!");
        }
        try
        {
            return newDocument(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static Document newDocument(final File file)
    {
        InputStream stream;
        try
        {
            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            LOGGER.error("Invoke [newDocument] failed!", e);
            throw new LoggedException("Invoke [newDocument] failed!");
        }
        try
        {
            return newDocument(stream);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static Document newDocument(final InputStream stream)
    {
        try
        {
            return newDocumentBuilder().parse(stream);
        }
        catch (SAXException e)
        {
            LOGGER.error("Invoke [newDocument] failed!", e);
            throw new LoggedException("Invoke [newDocument] failed!");
        }
        catch (IOException e)
        {
            LOGGER.error("Invoke [newDocument] failed!", e);
            throw new LoggedException("Invoke [newDocument] failed!");
        }
    }

    public static Document newDocument()
    {
        return newDocumentBuilder().newDocument();
    }

    public static Transformer newTransformer()
    {
        try
        {
            Transformer transformer = newTransformerFactory().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            return transformer;
        }
        catch (TransformerConfigurationException e)
        {
            LOGGER.error("Invoke [newTransformer] failed!", e);
            throw new LoggedException("Invoke [newTransformer] failed!");
        }
    }

    public static TransformerFactory newTransformerFactory()
    {
        return TransformerFactory.newInstance();
    }

    public static DocumentBuilderFactory newDocumentBuilderFactory()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try
        {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        }
        catch (ParserConfigurationException e)
        {
            LOGGER.error(EXCEPTION_MESSAGE_NEW_DOCUMENT_BUILDER_FACTORY, e);
            throw new LoggedException(EXCEPTION_MESSAGE_NEW_DOCUMENT_BUILDER_FACTORY);
        }
        return factory;
    }

    public static DocumentBuilder newDocumentBuilder()
    {
        try
        {
            return newDocumentBuilderFactory().newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            LOGGER.error("Invoke [newDocumentBuilder] failed!", e);
            throw new LoggedException("Invoke [newDocumentBuilder] failed!");
        }
    }

    public static String toEntity(final String value)
    {
        if (value == null)
        {
            return null;
        }
        if (value.length() == 0)
        {
            return "";
        }
        final char[] cs = value.toCharArray();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0, l = cs.length; i < l; i++)
        {
            final char c1 = cs[i];
            int cp = c1;
            final int index = i + 1;
            if (Character.isHighSurrogate(c1) && (index < l))
            {
                final char c2 = cs[index];
                if (Character.isLowSurrogate(c2))
                {
                    cp = Character.toCodePoint(c1, c2);
                    i = index;
                }
            }
            builder.append("&#");
            builder.append(Integer.toString(cp));
            builder.append(';');
        }
        return builder.toString();
    }
}
