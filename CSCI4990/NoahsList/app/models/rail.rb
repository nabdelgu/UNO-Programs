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

class Rail < ActiveRecord::Base

end
