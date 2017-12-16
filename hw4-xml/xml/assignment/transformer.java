import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class transformer {

	public static void main(String[] args) {
				String XSLFILE = args[0];
				String INFILE =  args[1];
				String OUTFILE = args[2];
				
				StreamSource xslcode =
						new StreamSource(new File(XSLFILE));
				StreamSource input = new StreamSource(new File(INFILE));
				StreamResult output = new StreamResult(new File(OUTFILE));
				

				TransformerFactory tf =
						TransformerFactory
						.newInstance();

				Transformer trans;
				try {
					trans = tf.newTransformer(xslcode);
					trans.transform(input, output);

				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
				
			}

	}

