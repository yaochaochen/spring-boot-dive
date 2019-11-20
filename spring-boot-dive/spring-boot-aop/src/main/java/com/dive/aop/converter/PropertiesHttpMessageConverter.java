package com.dive.aop.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {


    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }


    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

        //获取请求头
        HttpHeaders headers = httpOutputMessage.getHeaders();
        //获取content-type
        MediaType mediaType = headers.getContentType();
        //获取编码
        Charset charset = Charset.forName("UTF-8");
        if (charset != null) {
            charset = mediaType.getCharset();
        }
        //获取请求体
        OutputStream body = httpOutputMessage.getBody();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);
        properties.store(outputStreamWriter, "Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        Properties properties = new Properties();
        HttpHeaders headers = httpInputMessage.getHeaders();
        //获取content-type
        MediaType mediaType = headers.getContentType();
        //获取编码
        Charset charset = Charset.forName("UTF-8");
        if (charset != null) {
            charset = mediaType.getCharset();
        }
        //获取请求体
        InputStream body = httpInputMessage.getBody();
        InputStreamReader outputStreamWriter = new InputStreamReader(body, charset);
        properties.load(outputStreamWriter);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, httpInputMessage);
    }
}
