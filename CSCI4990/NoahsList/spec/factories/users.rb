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

FactoryGirl.define do
  factory :user do
    name "MyString"
email "MyString"
  end

end
