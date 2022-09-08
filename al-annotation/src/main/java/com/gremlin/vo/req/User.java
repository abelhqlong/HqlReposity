package com.gremlin.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @className: User
 * @author: gremlin
 * @version: 1.0.0
 * @description: User实体类及请求数据转为xml格式输出日志
 * @date: 2022/8/15 15:57
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@XmlType(propOrder = { "id", "name" })
public class User implements Serializable {

    private static final long serialVersionUID = 1858480386585537937L;

    private Integer id;
    private String name;

}
