/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.managers.plugin.data;

import com.alee.utils.CompareUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * Plugin version data class.
 *
 * @author Mikle Garin
 */

@XStreamAlias ("PluginVersion")
public class PluginVersion implements Serializable
{
    /**
     * Simple default v1.0.0.
     */
    public static final PluginVersion DEFAULT = new PluginVersion ( 1, 0, 0 );

    /**
     * Major plugin version.
     */
    @XStreamAsAttribute
    private int major;

    /**
     * Minor plugin version.
     */
    @XStreamAsAttribute
    private int minor;

    /**
     * Plugin build version.
     */
    @XStreamAsAttribute
    private Integer build;

    /**
     * Constructs new plugin version data object.
     */
    public PluginVersion ()
    {
        super ();
        this.major = DEFAULT.major;
        this.minor = DEFAULT.minor;
        this.build = DEFAULT.build;
    }

    /**
     * Constructs new plugin version data object with the specified major and minor version numbers.
     *
     * @param major major version number
     * @param minor minor version number
     */
    public PluginVersion ( final int major, final int minor )
    {
        super ();
        this.major = major;
        this.minor = minor;
        this.build = null;
    }

    /**
     * Constructs new plugin version data object with the specified major and minor version numbers.
     *
     * @param major major version number
     * @param minor minor version number
     * @param build build version number
     */
    public PluginVersion ( final int major, final int minor, final Integer build )
    {
        super ();
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

    public int getMajor ()
    {
        return major;
    }

    public void setMajor ( final int major )
    {
        this.major = major;
    }

    public int getMinor ()
    {
        return minor;
    }

    public void setMinor ( final int minor )
    {
        this.minor = minor;
    }

    public Integer getBuild ()
    {
        return build;
    }

    public void setBuild ( final Integer build )
    {
        this.build = build;
    }

    public boolean isNewerThan ( final PluginVersion ov )
    {
        return this.major > ov.major ||
                this.major == ov.major && this.minor > ov.minor ||
                this.major == ov.major && this.minor == ov.minor && this.build != null && ov.build == null ||
                this.major == ov.major && this.minor == ov.minor && this.build == null && ov.build != null && this.build > ov.build;
    }

    public boolean isOlderThan ( final PluginVersion ov )
    {
        return ov.major > this.major ||
                ov.major == this.major && ov.minor > this.minor ||
                ov.major == this.major && ov.minor == this.minor && ov.build != null && this.build == null ||
                ov.major == this.major && ov.minor == this.minor && ov.build != null && this.build != null && ov.build > this.build;
    }

    public boolean isSame ( final PluginVersion ov )
    {
        return ov.major == this.major && ov.minor == this.minor && CompareUtils.equals ( ov.build, this.build );
    }

    @Override
    public String toString ()
    {
        return "v" + major + "." + minor + ( build != null ? ( "." + build ) : "" );
    }
}