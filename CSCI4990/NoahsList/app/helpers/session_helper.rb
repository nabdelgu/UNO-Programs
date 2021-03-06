module SessionHelper
  def log_in(user)
    session[:user_id] = user.id
  end

  def current_user
    @current_user ||= User.find_by(id: session[:user_id])
  end

  def logged_in?
    !current_user.nil?
  end

  def redirect_to_login
    redirect_to login_path
  end

  def redirect_to_products
    redirect_to view_products_path
  end

  def is_admin?
    current_user.try(:adminUser)
  end



end
