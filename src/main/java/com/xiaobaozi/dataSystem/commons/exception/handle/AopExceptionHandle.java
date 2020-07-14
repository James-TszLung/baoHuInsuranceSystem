package com.xiaobaozi.dataSystem.commons.exception.handle;

import java.awt.AWTException;
import java.awt.FontFormatException;
import java.awt.datatransfer.MimeTypeParseException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.activation.ActivationException;
import java.rmi.server.ServerNotActiveException;
import java.security.GeneralSecurityException;
import java.security.PrivilegedActionException;
import java.security.acl.AclNotFoundException;
import java.security.acl.LastOwnerException;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.TooManyListenersException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.zip.DataFormatException;

import javax.annotation.Resource;
import javax.management.BadAttributeValueExpException;
import javax.management.BadBinaryOpValueExpException;
import javax.management.BadStringOperationException;
import javax.management.IntrospectionException;
import javax.management.InvalidApplicationException;
import javax.management.JMException;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.modelmbean.XMLParseException;
import javax.naming.NamingException;
import javax.print.PrintException;
import javax.script.ScriptException;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.RefreshFailedException;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.cert.CertificateException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.tree.ExpandVetoException;
import javax.transaction.xa.XAException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.URIReferenceException;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathException;

import jxl.common.Logger;

import org.ietf.jgss.GSSException;
import org.omg.CORBA.UserException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.RemarshalException;
import org.springframework.aop.ThrowsAdvice;
import org.xml.sax.SAXException;

import com.xiaobaozi.dataSystem.commons.exception.dao.ExceptionDao;
import com.xiaobaozi.dataSystem.commons.exception.vo.ExceptionVO;

public class AopExceptionHandle implements ThrowsAdvice {

	@Resource
	private ExceptionDao exceptionDao;
	
	/*private final static Object[] exceptions = new Object[]{AclNotFoundException.class, ActivationException.class, AlreadyBoundException.class, ApplicationException.class, AWTException.class, BackingStoreException.class,
		BadAttributeValueExpException.class, BadBinaryOpValueExpException.class, BadLocationException.class, 
		BadStringOperationException.class, BrokenBarrierException.class, CertificateException.class, 
		ClassNotFoundException.class, CloneNotSupportedException.class, DataFormatException.class, 
		DatatypeConfigurationException.class, DestroyFailedException.class, ExecutionException.class, 
		ExpandVetoException.class, FontFormatException.class, GeneralSecurityException.class,
		GSSException.class, IllegalAccessException.class, IllegalClassFormatException.class, 
		InstantiationException.class, InterruptedException.class, IntrospectionException.class, 
		InvalidApplicationException.class, InvalidMidiDataException.class, 
		InvalidPreferencesFormatException.class, InvalidTargetObjectTypeException.class, 
		InvocationTargetException.class, IOException.class, JAXBException.class, JMException.class,
		KeySelectorException.class, LastOwnerException.class, LineUnavailableException.class, 
		MarshalException.class, MidiUnavailableException.class, MimeTypeParseException.class, 
		NamingException.class, NoninvertibleTransformException.class, NoSuchFieldException.class,
		NoSuchMethodException.class, NotBoundException.class, NotOwnerException.class, 
		ParseException.class, ParserConfigurationException.class, PrinterException.class, 
		PrintException.class, PrivilegedActionException.class, PropertyVetoException.class, 
		RefreshFailedException.class, RemarshalException.class, RuntimeException.class, SAXException.class, 
		ScriptException.class, ServerNotActiveException.class, SOAPException.class, SQLException.class,
		TimeoutException.class, TooManyListenersException.class, TransformerException.class, 
		TransformException.class, UnmodifiableClassException.class, UnsupportedAudioFileException.class,
		UnsupportedCallbackException.class, UnsupportedFlavorException.class, UnsupportedLookAndFeelException.class, URIReferenceException.class, URISyntaxException.class, UserException.class, XAException.class, XMLParseException.class, XMLSignatureException.class, XMLStreamException.class, XPathException.class
	};*/
	
	private Logger logger = Logger.getLogger(getClass());
	
	public void setExceptionDao(ExceptionDao exceptionDao) {
		this.exceptionDao = exceptionDao;
	}

	/**
	 * 捕获异常
	 * @param m
	 * @param args
	 * @param target
	 * @param exception
	 */
	public void afterThrowing(Method m, Object[] args, Object target, Throwable e) {
		
		if(isNeedExceptionLog(e)){//异常入库
			addexceptionlog(m, args, target, e);
		}
		//异常日志
		if(null!=e.getCause()){
			logger.error(target.getClass().getName()+"."+m.getName()
					+"("+Arrays.toString(args)+") "+e.getCause().getClass().getSimpleName() + e.getMessage()); 
		}else{
			logger.error(target.getClass().getName()+"."+m.getName()
					+"("+Arrays.toString(args)+") " + e.getMessage()); 
		}
               
	}
	
	/**
	 * 异常入库操作
	 * @param m
	 * @param args
	 * @param target
	 * @param exception
	 */
	private void addexceptionlog(Method m, Object[] args, Object target, Throwable exception){
		ExceptionVO ev = new ExceptionVO();
		ev.setCreateTime(new Date());
		ev.setType(exception.getClass().getSimpleName());
		ev.setMsg(target.getClass().getName()+"."+m.getName()+"("+Arrays.toString(args)+") throws:"+exception.getMessage());
		try {
			exceptionDao.insert(ev);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("exceptionDao insert data:"+ev.toString()+", throws "+ e.getMessage());
		}
	}
	
	/**
	 * 判断异常是否需要入库
	 * @param e
	 * @return
	 */
	private boolean isNeedExceptionLog(Throwable e ){
		//TODO:需要列出哪些异常需要入库
		//暂定AclNotFoundException, ActivationException, AlreadyBoundException, ApplicationException, AWTException, BackingStoreException, BadAttributeValueExpException, BadBinaryOpValueExpException, BadLocationException, BadStringOperationException, BrokenBarrierException, CertificateException, ClassNotFoundException, CloneNotSupportedException, DataFormatException, DatatypeConfigurationException, DestroyFailedException, ExecutionException, ExpandVetoException, FontFormatException, GeneralSecurityException, GSSException, IllegalAccessException, IllegalClassFormatException, InstantiationException, InterruptedException, IntrospectionException, InvalidApplicationException, InvalidMidiDataException, InvalidPreferencesFormatException, InvalidTargetObjectTypeException, InvocationTargetException, IOException, JAXBException, JMException, KeySelectorException, LastOwnerException, LineUnavailableException, MarshalException, MidiUnavailableException, MimeTypeParseException, NamingException, NoninvertibleTransformException, NoSuchFieldException, NoSuchMethodException, NotBoundException, NotOwnerException, ParseException, ParserConfigurationException, PrinterException, PrintException, PrivilegedActionException, PropertyVetoException, RefreshFailedException, RemarshalException, RuntimeException, SAXException, ScriptException, ServerNotActiveException, SOAPException, SQLException, TimeoutException, TooManyListenersException, TransformerException, TransformException, UnmodifiableClassException, UnsupportedAudioFileException, UnsupportedCallbackException, UnsupportedFlavorException, UnsupportedLookAndFeelException, URIReferenceException, URISyntaxException, UserException, XAException, XMLParseException, XMLSignatureException, XMLStreamException, XPathException
		if(null!=e.getCause() && (e.getCause() instanceof AclNotFoundException|| e.getCause() instanceof ActivationException|| e.getCause() instanceof AlreadyBoundException|| e.getCause() instanceof ApplicationException|| e.getCause() instanceof AWTException|| e.getCause() instanceof BackingStoreException|| e.getCause() instanceof
				BadAttributeValueExpException|| e.getCause() instanceof BadBinaryOpValueExpException|| e.getCause() instanceof BadLocationException|| e.getCause() instanceof 
				BadStringOperationException|| e.getCause() instanceof BrokenBarrierException|| e.getCause() instanceof CertificateException|| e.getCause() instanceof 
				ClassNotFoundException|| e.getCause() instanceof CloneNotSupportedException|| e.getCause() instanceof DataFormatException|| e.getCause() instanceof 
				DatatypeConfigurationException|| e.getCause() instanceof DestroyFailedException|| e.getCause() instanceof ExecutionException|| e.getCause() instanceof 
				ExpandVetoException|| e.getCause() instanceof FontFormatException|| e.getCause() instanceof GeneralSecurityException|| e.getCause() instanceof
				GSSException|| e.getCause() instanceof IllegalAccessException|| e.getCause() instanceof IllegalClassFormatException|| e.getCause() instanceof 
				InstantiationException|| e.getCause() instanceof InterruptedException|| e.getCause() instanceof IntrospectionException|| e.getCause() instanceof 
				InvalidApplicationException|| e.getCause() instanceof InvalidMidiDataException|| e.getCause() instanceof 
				InvalidPreferencesFormatException|| e.getCause() instanceof InvalidTargetObjectTypeException|| e.getCause() instanceof 
				InvocationTargetException|| e.getCause() instanceof IOException|| e.getCause() instanceof JAXBException|| e.getCause() instanceof JMException|| e.getCause() instanceof
				KeySelectorException|| e.getCause() instanceof LastOwnerException|| e.getCause() instanceof LineUnavailableException|| e.getCause() instanceof 
				MarshalException|| e.getCause() instanceof MidiUnavailableException|| e.getCause() instanceof MimeTypeParseException|| e.getCause() instanceof 
				NamingException|| e.getCause() instanceof NoninvertibleTransformException|| e.getCause() instanceof NoSuchFieldException|| e.getCause() instanceof
				NoSuchMethodException|| e.getCause() instanceof NotBoundException|| e.getCause() instanceof NotOwnerException|| e.getCause() instanceof 
				ParseException|| e.getCause() instanceof ParserConfigurationException|| e.getCause() instanceof PrinterException|| e.getCause() instanceof 
				PrintException|| e.getCause() instanceof PrivilegedActionException|| e.getCause() instanceof PropertyVetoException|| e.getCause() instanceof 
				RefreshFailedException|| e.getCause() instanceof RemarshalException|| e.getCause() instanceof RuntimeException|| e.getCause() instanceof SAXException|| e.getCause() instanceof 
				ScriptException|| e.getCause() instanceof ServerNotActiveException|| e.getCause() instanceof SOAPException|| e.getCause() instanceof SQLException|| e.getCause() instanceof
				TimeoutException|| e.getCause() instanceof TooManyListenersException|| e.getCause() instanceof TransformerException|| e.getCause() instanceof 
				TransformException|| e.getCause() instanceof UnmodifiableClassException|| e.getCause() instanceof UnsupportedAudioFileException|| e.getCause() instanceof
				UnsupportedCallbackException|| e.getCause() instanceof UnsupportedFlavorException|| e.getCause() instanceof UnsupportedLookAndFeelException|| e.getCause() instanceof URIReferenceException|| e.getCause() instanceof URISyntaxException|| e.getCause() instanceof UserException|| e.getCause() instanceof XAException|| e.getCause() instanceof XMLParseException|| e.getCause() instanceof XMLSignatureException|| e.getCause() instanceof XMLStreamException|| e.getCause() instanceof XPathException)){
			return true;
		}		
		return false;
	}
	
}
