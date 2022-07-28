package cii.da.auth;

import cii.da.message.codec.Protocol;

import java.util.Map;

/**
 * 身份验证
 *
 * @author MFine
 * @date 2021/08/23
 */
public interface Authenticator extends Protocol {
    /**
     * TODO:待实现
     *
     * 进行身份验证
     *
     * @return {@link Boolean}
     */
    default Boolean authenticate(Map<String,Object> headers) {
        return true;
    }
}
