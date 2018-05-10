package com.liferay.training.UserModel;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author krisztian.rostas
 */
@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = ModelListener.class
)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterCreate(User model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
		System.out.println("*** New user has been created ***");
		
		super.onAfterCreate(model);
	}

	@Override
	public void onAfterUpdate(User model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
		System.out.println("*** User info has been UPDATED ***");
		
		super.onAfterUpdate(model);
	}


	// TODO enter required service methods
	
	
	

}