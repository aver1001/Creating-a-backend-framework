package web.controller;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlBeanFactory {
	/*
	 * 자바 스프링같은 프레임워크로 인해
	 * 최악의 코드 작성은 막을수 있음.
	 * 하지만 case 마다 컨트롤러(서블릿)를 만들어야하고
	 * 이건 case마다 객체를 생성해야하는 문제가 생김.
	 * 
	 * 그럼 프레임워크는 무조건 나쁜것인가?
	 * 개발을 혼자하는 경우는 드뭄.
	 * 이럴경우 정형화되어있는 프레임워크를 사용하게 된다면,
	 * 일정수준의 품질은 보장하는 코드작성이 가능함(스프링에서는 다 싱글톤으로 되어있음)
	 * 
	 * 생각의 결론.
	 * 프레임워크가 당연하게도 성능적으로 나쁨.
	 * jvm의 old 영역에 객체들이 더 많이 만들어지고,
	 * Parsing 자체의 OverHead가 높기 때문.
	 * 
	 * 하지만 다수가 협업하고, 같이 개발하는 상황에서 프레임워크는
	 * 장점을 충분히 보일 수 있음.
	 * 
	 * 프레임워크는 결국 그 안의 내부 기능들을 사용하는것이고.
	 * 우리가 내부 구조를 알지 못한상태로 프레임워크만 사용하는것은 잘못된 것.
	 * 내부구조를 알아야. 더 유연하게 대처할수 있고,
	 * 메모리를 극적으로 아껴야 하는상황에서 대처할수 있다.
	 * 
	 * 즉 스프링 개발자가 서블릿 할줄 몰라요. 한다는것은 정상적이지 않은 상황.
	 */
	HashMap<String,Controller> beans = new HashMap<>();

	public XmlBeanFactory(String path) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(path, new MyDefaultHandler());
	}
	public HashMap<String,Controller> get(){
		return beans;
	}
	
	class MyDefaultHandler extends DefaultHandler{
		String id;
		String className;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			/*
			 * 파싱은 무조건 start에서 해야함.
			 * element 중간에 데이터가  있을수 있음.
			 */
			if("bean".equals(qName)) {
				id =  attributes.getValue("id");
				className =  attributes.getValue("class");
				System.out.println(id +":" +className);
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			/*
			 * Java Reflection
			 * 대부분의 프레임워크들은
			 * Java Refelection을 사용함.
			 * 
			 * java.lang.Class
			 */
			if("bean".equals(qName)) {
				//기본 객체가 생성됨
				try {
					Controller o = (Controller) Class.forName(className).getConstructor().newInstance();
					beans.put(id, o);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void endDocument() throws SAXException {
			System.out.println(beans);
		}
		
	}
//	
//	public static void main(String[] args) throws Exception {
//		new XmlBeanFactory("beans.xml");
//	}
}
