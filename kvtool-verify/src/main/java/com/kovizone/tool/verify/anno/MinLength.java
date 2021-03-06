package com.kovizone.tool.verify.anno;

import com.kovizone.tool.verify.api.anno.Processor;
import com.kovizone.tool.verify.processor.MinLengthProcessor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标记字符串验证限定最小的长度，value为限定长度值<BR>
 * 验证顺序在@FixedLength之后<BR>
 *
 * @author KoviChen (kovichen@163.com)
 * @version 0.0.1 20191113 KoviChen 新建类
 */
@Processor(MinLengthProcessor.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface MinLength {
    int value();
}
