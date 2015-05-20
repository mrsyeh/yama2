
package org.meruvian.yama.web.security.oauth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;


public interface OauthApplicationApprovalService extends ApprovalStore {
	OauthApplicationApproval getApprovalById(String id);
	
	OauthApplicationApproval getApprovalByUserAndClientAndScope(String userId, String clientId, String scope);
	
	Page<? extends OauthApplicationApproval> findApprovalByUserAndClient(String userId, String clientId, Pageable pageable);
	
	Page<? extends OauthApplicationApproval> findApprovalByClient(String clientId, Pageable pageable);
	
	Page<? extends OauthApplicationApproval> findApprovalByUser(String userId, Pageable pageable);
}
