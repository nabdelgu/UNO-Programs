# == Schema Information
#
# Table name: users
#
#  id              :integer          not null, primary key
#  first_name      :string
#  last_name       :string
#  email           :string
#  password        :string
#  created_at      :datetime         not null
#  updated_at      :datetime         not null
#  password_digest :string
#  AdminUser       :boolean
#  adminUser       :boolean
#

class User < ActiveRecord::Base
  
  has_secure_password
  has_many :products
  has_many :checkouts

  before_save { self.email = email.downcase }
  VALID_EMAIL_REGEX = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates_presence_of :first_name, presence: true, length: { maximum: 25 }
  validates_presence_of :last_name, presence: true, length: { maximum: 25 }
  validates_presence_of :email, presence: true, length: { maximum: 255 },
            format: { with: VALID_EMAIL_REGEX },
            uniqueness: {case_sensitive: false}

  validates :password, length: { minimum: 6 }

  def full_name
    "#{self.first_name} #{self.last_name}"
  end


end
