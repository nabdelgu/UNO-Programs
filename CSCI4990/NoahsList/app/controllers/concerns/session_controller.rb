class SessionController < ApplicationController
  def new
  end

  def create
    user = User.authenticate(params[:session][:email], params[:session][:password])

    if user && user.authenticate(params[:session][:password])
      #log user in
      log_in user
      redirect_to_products
    else
      flash[:danger] = 'Invalid email/password combination' # Not quite right!
      redirect_to_products
    end
  end

  def destroy
    session[:user_id] = nil
    flash[:notice] = 'Logged out successfully'
    redirect_to_products
  end

end