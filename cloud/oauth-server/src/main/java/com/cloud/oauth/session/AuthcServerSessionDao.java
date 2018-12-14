package com.cloud.oauth.session;

import com.cloud.oauth.service.RedisService;
import com.cloud.oauth.util.SessionUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

/**
 * 基于redis的sessionDao，缓存共享session.
 *
 * @author xujiping 2017-10-19 10:21
 */
@Component
public class AuthcServerSessionDao extends CachingSessionDAO {

    private static Logger _LOGGER = LoggerFactory.getLogger(AuthcServerSessionDao.class);

    //会话key
    private final static String AUTHC_SERVER_SHIRO_SESSION_ID = "authc-server-shiro-session-id";

    //全局会话key
    private final static String AUTHC_SERVER_GLOBAL_SESSION_ID = "authc-server-global-session-id";

    //全局会话列表key
    private final static String AUTHC_SERVER_GLOBAL_SESSION_IDS = "authc-server-golbal-session-ids";

    // code key
    private final static String AUTHC_SERVER__CODE = "authc-server-code";

    // 局部会话key
    private final static String AUTHC_CLIENT_SESSION_ID = "authc-client-com.rb.oauth.session-id";

    // 单点同一个code所有局部会话key
    private final static String AUTHC_CLIENT_SESSION_IDS = "authc-client-com.rb.oauth.session-ids";

    @Autowired
    private RedisService redisService;

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        AuthcServerSession authcServerSession = (AuthcServerSession) session;
        AuthcServerSession cacheSession = (AuthcServerSession) doReadSession(session.getId());
        if (cacheSession != null) {
            authcServerSession.setStatus(cacheSession.getStatus());
            authcServerSession.setAttribute("FORCE_LOGOUT", cacheSession.getAttribute("FORCE_LOGOUT"));
        }
        redisService.set(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + session.getId(), SessionUtil
                .serialize(session), session.getTimeout() / 1000);
        _LOGGER.debug("doUpdate >>>>> sessionId={}", session.getId());

    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        // 当前全局会话code
        Object code = redisService.get(AUTHC_SERVER_GLOBAL_SESSION_ID + "_" + sessionId);
        // 清除全局会话
        redisService.remove(AUTHC_SERVER_GLOBAL_SESSION_ID + "_" + sessionId);
        // 清除code校验值
        redisService.remove(AUTHC_SERVER__CODE + "_" + String.valueOf(code));
        // 清除所有局部会话
        Set<Object> clientSessionIds = redisService.setMembers(AUTHC_CLIENT_SESSION_IDS + "_" + code);
        for (Object clientSessionId : clientSessionIds) {
            redisService.remove(AUTHC_CLIENT_SESSION_ID + "_" + String.valueOf(clientSessionId));
            redisService.remove(AUTHC_CLIENT_SESSION_IDS + "_" + code, String.valueOf(clientSessionId));
        }
        // 删除session
        redisService.remove(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + sessionId);
        _LOGGER.debug("doUpdate >>>>> sessionId={}", sessionId);
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisService.set(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + sessionId, SessionUtil.serialize
                (session), session.getTimeout() / 1000);
        _LOGGER.debug("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Object session = redisService.get(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + sessionId);
        _LOGGER.debug("doReadSession >>>>> sessionId={}", sessionId);
        //反序列化并返回
        return SessionUtil.deserialize(String.valueOf(session));
    }

    /**
     * 强制退出
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            Object session = redisService.get(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + sessionId);
            AuthcServerSession upmsSession = (AuthcServerSession) SessionUtil.deserialize(String.valueOf(session));
            upmsSession.setStatus(AuthcServerSession.OnlineStatus.force_logout);
            upmsSession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            redisService.set(AUTHC_SERVER_SHIRO_SESSION_ID + "_" + sessionId, SessionUtil.serialize
                    (upmsSession), upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }
}
