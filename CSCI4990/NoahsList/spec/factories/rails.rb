# == Schema Information
#
# Table name: rails
#
#  id                 :integer          not null, primary key
#  generate           :string
#  model              :string
#  User               :string
#  name               :string
#  email              :string
#  encrypted_password :string
#  salt               :string
#  created_at         :datetime         not null
#  updated_at         :datetime         not null
#

FactoryGirl.define do
  factory :rail do
    generate "MyString"
model "MyString"
User "MyString"
name "MyString"
email "MyString"
encrypted_password "MyString"
salt "MyString"
  end

end
